package tsuyoyo.fikaflag.services

import org.springframework.stereotype.Service
import tsuyoyo.fikaflag.domain.FikaFlag
import tsuyoyo.fikaflag.domain.JoinPost

import javax.annotation.PostConstruct

@Service
class FlagsService implements IFlagsService {
	
	private Map<UUID, FikaFlag> mFlags;
	
	@PostConstruct
	public init() {
		mFlags = new HashMap<UUID, FikaFlag>();
	}

	@Override
	public Map<UUID, FikaFlag> get() {
		return mFlags;
	}

	@Override
	public FikaFlag get(UUID id) {
		return mFlags.get(id);
	}

	@Override
	public UUID post(FikaFlag flag) {
		UUID uuid = UUID.randomUUID();
		while (mFlags.get(uuid)) {
			uuid = UUID.randomUUID();
		}
		mFlags.put(uuid, flag);
	}

	@Override
	public int join(JoinPost joinPost) {
		mFlags.get(joinPost.flagId)?.attendees.add(joinPost.sessionId);
	}

	@Override
	public void cancel(JoinPost joinPost) {
		mFlags.get(joinPost.flagId)?.attendees.remove(joinPost.sessionId);
	}

	@Override
	public void delete(UUID flagId) {
		mFlags.remove(flagId);
	}

}

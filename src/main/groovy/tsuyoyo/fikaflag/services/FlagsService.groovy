package tsuyoyo.fikaflag.services

import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import tsuyoyo.fikaflag.domain.FikaFlag
import tsuyoyo.fikaflag.domain.JoinPost

import javax.annotation.PostConstruct
import java.util.concurrent.Future
import java.util.function.Predicate

@Service
class FlagsService implements IFlagsService {
	
	private List<FikaFlag> mFlags;
	
	@PostConstruct
	public init() {
		mFlags = new ArrayList<FikaFlag>();
	}

	@Override
	public List<FikaFlag> get() {
		return mFlags;
	}

	@Override
	public FikaFlag get(UUID id) {
		return mFlags.find { it.uuid.equals(id); };
	}

	@Override
	public UUID post(FikaFlag flag) {
        flag.uuid = UUID.randomUUID();;
        mFlags.push(flag);
        return flag.uuid;
	}

    RestTemplate restTemplate = new RestTemplate();

    @Async
    @Override
	public int join(JoinPost joinPost, boolean now) {

		if (joinPost.name) {
			mFlags.find {
				return it.uuid.equals(joinPost.flagId)
			}?.attendees?.add(joinPost.name)
		}

        String host = mFlags.find { return it.uuid.equals(joinPost.flagId) }?.host;
        if (host) {
            String url = "http://" + host + "/join/" + (now ? "justnow" : "afterminutes");

			Map<String, String> args = new HashMap<String, String>();
			args.put("user", joinPost.name);

            return restTemplate.postForLocation(url, String.class, args);
        } else {
            throw new BadRequestException();
        }
	}

	@Override
	public void cancel(JoinPost joinPost) {
		mFlags.find { it.uuid.equals(joinPost.flagId); }.attendees.remove(joinPost.sessionId);
	}

	@Override
	public void delete(UUID flagId) {
		mFlags.removeIf(new Predicate<FikaFlag>() {
            boolean test(FikaFlag fikaFlag) {
                return fikaFlag.uuid.equals(flagId);
            }
        });
	}

}

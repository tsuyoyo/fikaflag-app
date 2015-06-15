package tsuyoyo.fikaflag.services

import java.util.Map;
import java.util.UUID;

import tsuyoyo.fikaflag.domain.FikaFlag
import tsuyoyo.fikaflag.domain.JoinPost

interface IFlagsService {
	
	/**
	 * 全てのflagを取得
	 * @return
	 */
	Map<UUID, FikaFlag> get();

	/**
	 * 指定したflagを取得
	 * @param id
	 * @return
	 */
	FikaFlag get(UUID id);
	
	/**
	 * 新しいFika情報を登録
	 * @param flag
	 */
	UUID post(FikaFlag flag);
	
	/**
	 * 参加表明を登録
	 * @param joinPost
	 * @return entry ID
	 */
	int join(JoinPost joinPost);

	/**
	 * 参加を取りやめ
	 * @param flagId
	 * @param entryId
	 */
	void cancel(JoinPost joinPost);
	
	/**
	 * Flagを取り下げる
	 * @param flagId
	 */
	void delete(UUID flagId);

}

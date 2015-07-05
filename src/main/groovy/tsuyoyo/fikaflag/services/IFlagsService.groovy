package tsuyoyo.fikaflag.services

import tsuyoyo.fikaflag.domain.FikaFlag
import tsuyoyo.fikaflag.domain.JoinPost

interface IFlagsService {
	
	/**
	 * 全てのflagを取得
	 * @return
	 */
	List<FikaFlag> get();

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
	 */
	int join(JoinPost joinPost, boolean now);

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

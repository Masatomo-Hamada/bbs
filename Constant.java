package keijiban;

/**
 * 定数クラス
 * @author M.Hamada
 * @version 1.00
 * @since JDK1.8.0
 */
public class Constant{
	
	/** ログインエラーメッセージ */
	public static final String WRONG_ENTRY_LOGIN = "ユーザーIDまたはパスワードが違います";
	
	/** データ不存在エラーメッセージ */
	public static final String NOT_EXIST_LIST = "投稿がありません";
	
	/** 本人確認エラーメッセージ */
	public static final String NO_MATCH_ID = "投稿の編集・削除は投稿者のみ可能です";
	
	/** チェックボックス未選択エラーメッセージ */
	public static final String NO_SELECT_MESSAGE = "投稿が選択されていません";
	
	/** タイトル未入力エラーメッセージ */
	public static final String NO_ENTRY_TITLE = "タイトルが入力されていません";
	
	/** タイトル文字数エラーメッセージ */
	public static final String OVER_ENTRY_TITLE = "タイトルは全角30字以内で入力してください";
	
	/** 本文未入力エラーメッセージ */
	public static final String NO_ENTRY_MESSAGE = "本文が入力されていません";
	
	/** 本文文字数エラーメッセージ */
	public static final String OVER_ENTRY_MESSAGE = "本文は全角300字以内で入力してください";

	/** ログイン画面遷移パス */
	public static final String LOGIN_PATH = "/login.jsp";
	
	/** 投稿一覧画面遷移パス */
	public static final String DISPLAY_PATH = "/display.jsp";
	
	/** 新規投稿画面遷移パス */
	public static final String ENTRY_PATH = "/entry.jsp";
	
	/** 投稿編集画面遷移パス */
	public static final String EDIT_PATH = "/edit.jsp";
	
}

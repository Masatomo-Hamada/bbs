package keijiban;

import java.sql.Timestamp;

/**
 * JavaBeans
 */
public class JavaBeans {

	/** 投稿番号フィールド */
	private int number;

	/** ユーザーIDフィールド */
	private String id;

	/** 氏名フィールド */
	private String name;

	/** 投稿タイトルフィールド */
	private String title;

	/** 投稿本文フィールド */
	private String message;

	/** 投稿日時フィールド */
	private Timestamp tokoDate;

	/** 更新日時フィールド */
	private Timestamp editDate;

	/**
	 * 投稿番号フィールドに値を代入する
	 * 
	 * @param number
	 *            フィールドに代入する投稿番号
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * 投稿番号フィールドの値を参照する
	 * 
	 * @return this.number 投稿番号フィールドの値を返す
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * ユーザーIDフィールドに値を代入する
	 * 
	 * @param id
	 *            フィールドに代入するユーザーID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ユーザーIDフィールドの値を参照する
	 * 
	 * @return this.id ユーザーIDフィールドの値を返す
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 氏名フィールドに値を代入する
	 * 
	 * @param name
	 *            フィールドに代入する氏名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 氏名フィールドの値を参照する
	 * 
	 * @return this.name 氏名フィールドの値を返す
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 投稿タイトルフィールドに値を代入する
	 * 
	 * @param title
	 *            フィールドに代入する投稿タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 投稿タイトルフィールドの値を参照する
	 * 
	 * @return this.title 投稿タイトルフィールドの値を返す
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 投稿本文フィールドに値を代入する
	 * 
	 * @param message
	 *            フィールドに代入する投稿本文
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 投稿本文フィールドの値を参照する
	 * 
	 * @return this.message 投稿本文フィールドの値を返す
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 投稿日時フィールドに値を代入する
	 * 
	 * @param tokoDate
	 *            フィールドに代入する投稿日時
	 */
	public void setTokoDate(Timestamp tokoDate) {
		this.tokoDate = tokoDate;
	}

	/**
	 * 投稿日時フィールドの値を参照する
	 * 
	 * @return this.tokoDate 投稿日時フィールドの値を返す
	 */
	public Timestamp getTokoDate() {
		return this.tokoDate;
	}

	/**
	 * 更新日時フィールドに値を代入する
	 * 
	 * @param editDate
	 *            フィールドに代入する更新日時
	 */
	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}

	/**
	 * 更新日時フィールドの値を参照する
	 * 
	 * @return this.editDate 更新日時フィールドの値を返す
	 */
	public Timestamp getEditDate() {
		return this.editDate;
	}
}

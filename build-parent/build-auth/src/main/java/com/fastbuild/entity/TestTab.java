package com.fastbuild.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 测试表
 * </p>
 *
 * @author xinqch
 * @since 2017-12-22
 */
@TableName("test_tab")
public class TestTab extends Model<TestTab> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String message;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TestTab{" +
			", id=" + id +
			", message=" + message +
			"}";
	}
}

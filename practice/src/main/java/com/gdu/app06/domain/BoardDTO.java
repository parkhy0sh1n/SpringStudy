package com.gdu.app06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO(Data Transfer Object) : 데이터를 주고 받는 Java Beans(Bean)이다.

// Getter, Setter, toString 등 자동 생성 애너테이션.
@Data
// 기본 생성자 자동 생성 애너테이션.
@NoArgsConstructor
// 모든 필드 값을 파라미터로 받는 생성자 자동 생성 애너테이션.
@AllArgsConstructor
public class BoardDTO {
	
	private int board_no;
	private String title;
	private String content;
	private String writer;
	private String created_at;
	private String modified_at;

}

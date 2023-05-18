package com.gdu.app10.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PageUtil {

	private int page;              // 현재 페이지(파라미터로 받아온다)
	private int totalRecord;       // 전체 레코드 개수(DB에서 구해온다)
	private int recordPerPage;     // 한 페이지에 표시할 레코드 개수(파라미터로 받아온다)
	private int begin;             // 한 페이지에 표시할 레코드의 시작 번호(계산한다)
	private int end;               // 한 페이지에 표시할 레코드의 종료 번호(계산한다)
	
	private int pagePerBlock = 5;  // 한 블록에 표시할 페이지의 개수(임의로 정한다)
	private int totalPage;         // 전체 페이지 개수(계산한다)
	private int beginPage;         // 한 블록에 표시할 페이지의 시작 번호(계산한다)
	private int endPage;           // 한 블록에 표시할 페이지의 종료 번호(계산한다)
	
	public void setPageUtil(int page, int totalRecord, int recordPerPage) {
		
		// page, totalRecord, recordPerPage 저장
		this.page = page;
		this.totalRecord = totalRecord;
		this.recordPerPage = recordPerPage;
		
		// begin, end 계산
		/*
			totalRecord=26, recordPerPage=5인 상황
			page	begin	end
			1		1		5
			2		6		10
			3		11		15
			4		16		20
			5		21		25
			6		26		26
			
			페이지당 표시할 레코드 수(recordPage당 레코드 수), 
			총 레코드 수(totalRecord) 및 페이지 번호(page)를 고려하여 특정 페이지에 표시할 시작 및 종료 레코드 수를 계산하는 데 사용된다.
			시작 레코드 수는 페이지 번호에서 1을 뺀 값에 페이지당 레코드 수를 곱한 후 1을 더하면 계산된다. 
			예를 들어, 페이지 번호가 2이고 페이지당 레코드 수가 10이면 시작 레코드 번호는 (2-1)*10+1 = 11이 된다.
			종료 레코드 수는 시작 레코드 수에 페이지당 레코드 수를 더한 다음 1을 빼서 계산한다. 
			그러나 계산된 종료 레코드 수가 총 레코드 수보다 클 경우 대신 총 레코드 수로 설정된다. 
			마지막 페이지에 데이터 세트에 있는 것보다 더 많은 레코드가 표시되지 않도록 하기 위한 것이다.
			전반적으로 이 코드는 사용자의 입력에 따라 원하는 레코드만 특정 페이지에 표시되도록 하는 데 사용된다.
		*/
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		// totalPage 계산
		/*
			이 코드 조각은 페이지당 지정된 레코드 수('recordPage당')가 주어지면 데이터베이스의 모든 레코드를 표시하는 데 필요한 총 페이지 수를 계산한다.
			첫 번째 줄은 총 레코드 수('total Record')를 페이지당 레코드 수로 나누어 총 페이지 수를 계산한다. 
			이것은 정수 나눗셈 결과를 제공하며, 이는 나머지가 잘린다는 것을 의미한다.
			두 번째 줄은 나눗셈의 나머지가 있는지 확인한다. 
			만약 있다면, 전체 페이지를 채울 수 없는 일부 레코드가 남아 있다는 것을 의미한다. 
			이 경우 코드는 나머지 레코드를 표시하는 데 필요한 추가 페이지를 설명하기 위해 'totalPage' 변수를 1씩 늘린다.
			전체적으로 이 코드는 데이터베이스의 모든 레코드가 누락되거나 불완전한 데이터 없이 적절한 수의 페이지에 걸쳐 표시되도록 한다.
		*/
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		
		// beginPage, endPage 계산
		/*
			totalPage=6, pagePerBlock=4인 상황
			block(page)	beginPage	endPage
			1(1~4)		1			4
			2(5~6)		5			6
			
			이 코드는 현재 페이지 번호, 블록당 페이지 수 및 총 페이지 수를 고려할 때 페이지 블록의 시작 페이지와 끝 페이지를 계산하는 것으로 나타납니다.
			첫 번째 줄은 현재 페이지 번호에서 1을 뺀 페이지 수를 블록당 페이지 수로 나누고 가장 가까운 정수로 반올림한 다음 블록당 페이지 수를 곱하여 블록의 시작 페이지를 계산합니다. 
			끝에 있는 더하기 하나는 시작 페이지 번호를 블록의 첫 번째 페이지로 이동시킵니다.
			두 번째 줄은 시작 페이지에 블록당 페이지 수에서 1을 뺀 값을 추가하여 블록의 끝 페이지를 계산합니다. 
			결과 종료 페이지가 총 페이지 수보다 클 경우, 종료 페이지는 총 페이지 수로 설정됩니다.
			전체적으로 이 코드는 지정된 페이지 번호와 블록당 페이지 수에 대한 페이지 표시가 사용자에게 올바르게 업데이트되고 표시되도록 하는 데 도움이 됩니다.
		*/
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
	}
	
	public String getPagination(String path) {
		
		// path에 ?가 포함되어 있으면 이미 파라미터가 포함된 경로이므로 &를 붙여서 page 파라미터를 추가한다.
		
		if(path.contains("?")) {
			path += "&";  // path = "/app09/employees/pagination.do?order=ASC&"
		} else {
			path += "?";  // path = "/app09/employees/pagination.do?"
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class=\"pagination\">");
		
		// 이전 블록 : 1블록은 이전 블록이 없고, 나머지 블록은 이전 블록이 있다.
		if(beginPage == 1) {
			sb.append("<span class=\"hidden\">◀</span>");
		} else {
			sb.append("<a class=\"link\" href=\"" + path + "page=" + (beginPage - 1) + "\">◀</a>");
		}
		
		// 페이지번호 : 현재 페이지는 링크가 없다.
		for(int p = beginPage; p <= endPage; p++) {
			if(p == page) {
				sb.append("<span class=\"strong\">" + p + "</span>");
			} else {
				sb.append("<a class=\"link\" href=\"" + path + "page=" + p + "\">" + p + "</a>");
			}
		}
		
		// 다음 블록 : 마지막 블록은 다음 블록이 없고, 나머지 블록은 다음 블록이 있다.
		if(endPage == totalPage) {
			sb.append("<span class=\"hidden\">▶</span>");
		} else {
			sb.append("<a class=\"link\" href=\"" + path + "page=" + (endPage + 1) + "\">▶</a>");
		}
		
		sb.append("</div>");
		
		return sb.toString();
		
	}
	
}

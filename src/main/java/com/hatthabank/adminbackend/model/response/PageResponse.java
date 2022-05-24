package com.hatthabank.adminbackend.model.response;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hatthabank.sdk.web.model.response.BaseDataResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T, R> extends BaseDataResponse{
	List<R> rows;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	PageData page;

	public PageResponse(Page<T> queryResult, Function<? super T, ? extends R>  toResponse) {
		rows = queryResult.getContent().stream().map((Function<? super T, ? extends R>) toResponse).collect(Collectors.toList());
		page = PageData.builder()
				.pageNumber(queryResult.getNumber())
				.pageSize(queryResult.getSize())
				.totalElements(queryResult.getTotalElements())
				.totalPages(queryResult.getTotalPages())
				.build();
	}

	public PageResponse(Page<T> queryResult, BiFunction<T, String, R> fn, String lang) {
		rows =  queryResult.getContent().stream().map(i->fn.apply(i, lang)).collect(Collectors.toList());
		page = PageData.builder()
				.pageNumber(queryResult.getNumber())
				.pageSize(queryResult.getSize())
				.totalElements(queryResult.getTotalElements())
				.totalPages(queryResult.getTotalPages())
				.build();
	}

	@Data
	@Builder
	public static class PageData{
		private int pageNumber;
		private int pageSize;
		private long totalElements;
		private int totalPages;
	}
}

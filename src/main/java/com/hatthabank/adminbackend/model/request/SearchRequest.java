package com.hatthabank.adminbackend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hatthabank.adminbackend.search.SearchOperation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class SearchRequest {
	private String key;
	private Object value;
	@JsonProperty("search_operation")
	private SearchOperation searchOperation;
}

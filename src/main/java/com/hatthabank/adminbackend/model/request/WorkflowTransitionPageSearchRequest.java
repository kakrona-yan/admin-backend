package com.hatthabank.adminbackend.model.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class WorkflowTransitionPageSearchRequest {
    List<SearchRequest> params = new ArrayList<>();
    int pageNumber;
    int pageSize;
}

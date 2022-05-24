package com.hatthabank.adminbackend.model.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class WorkflowFunctionPageSearchRequest {
    List<SearchRequest> params = new ArrayList<>();
    int pageNumber;
    int pageSize;
}

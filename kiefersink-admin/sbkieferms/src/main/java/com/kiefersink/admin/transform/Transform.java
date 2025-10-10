package com.kiefersink.admin.transform;

public interface Transform <M, D>{
    M toModel(D data);
    D toData(M model);
}

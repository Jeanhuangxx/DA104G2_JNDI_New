package com.diveMessage.model;

import java.util.List;


public interface DiveMessage_interface {
    public void insert(DiveMessageVO diveMessageVO);
    public void update(DiveMessageVO diveMessageVO);
    public DiveMessageVO findByPrimaryKey(String divmeg_no);
    public List<DiveMessageVO> getAll();
}

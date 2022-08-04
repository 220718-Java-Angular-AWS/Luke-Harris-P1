package com.revature.services;

import com.revature.daos.RequestsDAO;
import com.revature.pojos.Requests;

import java.util.List;

public class RequestsService {
    private RequestsDAO dao;
    public RequestsService() {
        this.dao = new RequestsDAO();
    }
    public void saveRequests(Requests requests) {
        dao.create(requests);
    }
    public Requests getRequests(int id) {
        return dao.read(id);
    }
    public List<Requests> getAllRequests() {
        return dao.readAll();
    }
    public void updateRequests(Requests requests) {
        dao.update(requests);
    }
    public void deleteRequests(int id) {
        dao.delete(id);
    }
}

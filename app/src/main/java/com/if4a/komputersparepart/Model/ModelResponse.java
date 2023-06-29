package com.if4a.komputersparepart.Model;

import java.util.List;

public class ModelResponse {

    private String kode, pesan;
    private List<ModelKomputer> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelKomputer> getData() {
        return data;
    }
}

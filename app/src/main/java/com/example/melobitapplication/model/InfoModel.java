package com.example.melobitapplication.model;

public class InfoModel
{
    	String _postman_id,
                name,
                schema,
                exporter_id;

    public String get_postman_id() {
        return _postman_id;
    }

    public void set_postman_id(String _postman_id) {
        this._postman_id = _postman_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getExporter_id() {
        return exporter_id;
    }

    public void setExporter_id(String exporter_id) {
        this.exporter_id = exporter_id;
    }
}

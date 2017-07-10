package com.reminder.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.reminder.service.type.HttpMethod;
import com.reminder.service.type.RelValue;

/**
 * This class contains details necessary for HATEOAS link
 */

@JsonPropertyOrder({ "rel", "href", "method" })
@JsonInclude(Include.NON_EMPTY)
public class ResourceLink  {

    // the Hyper Text Reference
    private String href;
    // The relation
    private String rel;
    // The HTTP method
    private String method;

    public ResourceLink() {

    }

    public ResourceLink(final String href, final HttpMethod method, final RelValue rel) {
        this.rel = rel.toString();
        this.href = href;
        this.method = method.toString();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

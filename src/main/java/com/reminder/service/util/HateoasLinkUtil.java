package com.reminder.service.util;

import com.reminder.service.model.ResourceLink;
import com.reminder.service.type.HttpMethod;
import com.reminder.service.type.RelValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a utility class for creating HATEOAS links for the resources
 */
public class HateoasLinkUtil {

    private final static String CONTEXT_URI = "http://localhost:8080/reminder-service/v1/";
    private static Map<RelValue, List<ResourceLink>> linkMap = new HashMap<>();

    /**
     * Map the right methods for the type of the resource, get, search, add, or update
     * @param rel
     * @param resourceURI
     * @param param
     * @param filter
     * @return returns the List of resource link based on the resource type
     */
    public static List<ResourceLink> getHateoasLink(RelValue rel, String resourceURI, String param, String filter) {
        linkMap.put(RelValue.GET, getHateoasLinkForGet(resourceURI, param));
        linkMap.put(RelValue.SEARCH, getHateoasLinkForSearch(resourceURI, param, filter));
        linkMap.put(RelValue.CREATE, getHateoasLinkForAdd(resourceURI, param));
        linkMap.put(RelValue.UPDATE, getHateoasLinkForUpdate(resourceURI, param));
        return linkMap.get(rel);
    }

    /**
     * Prepares the HATEOAS links for the Get resource
     * @param resourceURI the resource URI
     * @param param the parameter to be attached to the links
     * @return the List of resource link
     */
    public static List<ResourceLink> getHateoasLinkForGet(String resourceURI, String param) {
        List<ResourceLink> resourceLinkList = new ArrayList<>();
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + param, HttpMethod.GET, RelValue.SELF));
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + param, HttpMethod.PUT, RelValue.UPDATE));
        return resourceLinkList;
    }

    /**
     * Prepares the HATEOAS links for the Search resource
     * @param resourceURI the resource URI
     * @param param the parameter to be attached to the links
     * @param filter the filter fields to be attached to the links
     * @return the List of resource link
     */
    public static List<ResourceLink> getHateoasLinkForSearch(String resourceURI, String param, String filter) {
        List<ResourceLink> resourceLinkList = new ArrayList<>();
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + filter, HttpMethod.GET, RelValue.SELF));
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + param, HttpMethod.PUT, RelValue.UPDATE));
        return resourceLinkList;
    }

    /**
     * Prepares the HATEOAS links for the Create resource
     * @param resourceURI the resource URI
     * @param param the parameter to be attached to the links
     * @return the List of resource link
     */
    public static List<ResourceLink> getHateoasLinkForAdd(String resourceURI, String param) {
        List<ResourceLink> resourceLinkList = new ArrayList<>();
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI, HttpMethod.POST, RelValue.SELF));
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + param, HttpMethod.GET, RelValue.GET));
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + param, HttpMethod.PUT, RelValue.UPDATE));
        return resourceLinkList;
    }

    /**
     * Prepares the HATEOAS links for the Update resource
     * @param resourceURI the resource URI
     * @param param the parameter to be attached to the links
     * @return the List of resource link
     */
    public static List<ResourceLink> getHateoasLinkForUpdate(String resourceURI, String param) {
        List<ResourceLink> resourceLinkList = new ArrayList<>();
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + param, HttpMethod.PUT, RelValue.SELF));
        resourceLinkList.add(new ResourceLink(CONTEXT_URI + resourceURI + param, HttpMethod.GET, RelValue.GET));
        return resourceLinkList;
    }
}

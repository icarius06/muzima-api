/*
 * Copyright (c) 2014. The Trustees of Indiana University.
 *
 * This version of the code is licensed under the MPL 2.0 Open Source license with additional
 * healthcare disclaimer. If the user is an entity intending to commercialize any application
 * that uses this code in a for-profit venture, please contact the copyright holder.
 */

package com.muzima.api.model;

import com.muzima.search.api.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Observation extends OpenmrsSearchable {

    private Person person;

    private Encounter encounter;

    private Concept concept;

    private Concept valueCoded;

    private Date valueDatetime;

    private Double valueNumeric;

    private String valueText;

    private Date observationDatetime;

    private boolean voided;
    
    private HashMap<String,String> editStatus;

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(final Encounter encounter) {
        this.encounter = encounter;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(final Concept concept) {
        this.concept = concept;
    }

    public Concept getValueCoded() {
        return valueCoded;
    }

    public void setValueCoded(final Concept valueCoded) {
        this.valueCoded = valueCoded;
    }

    public Date getValueDatetime() {
        return valueDatetime;
    }

    public void setValueDatetime(final Date valueDatetime) {
        this.valueDatetime = valueDatetime;
    }

    public Double getValueNumeric() {
        return valueNumeric;
    }

    public void setValueNumeric(final Double valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(final String valueText) {
        this.valueText = valueText;
    }

    /**
     * Get the date and time of the observation.
     *
     * @return the date and time of the observation.
     */
    public Date getObservationDatetime() {
        return observationDatetime;
    }

    /**
     * Set the date and time of the observation.
     *
     * @param observationDatetime the date and time of the observation.
     */
    public void setObservationDatetime(final Date observationDatetime) {
        this.observationDatetime = observationDatetime;
    }

    public String getValueAsString() {
        if (getConcept().getName().equals(StringUtil.EMPTY)) {
            throw new UnsupportedOperationException("The concept has not been loaded fully");
        }
        if (getConcept().isNumeric() && valueNumeric != null) {
            return getStringOfNumeric();
        } else if (getConcept().isDatetime() && valueDatetime != null) {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(valueDatetime);
        } else if (getConcept().isCoded() && valueCoded != null) {
            return getValueCoded().getName();
        } else {
            if (valueText != null)
                return valueText;
        }
        return "";
    }

    private String getStringOfNumeric() {
        if (getConcept().isPrecise())
            return valueNumeric.toString();
        else
            return String.valueOf(valueNumeric.intValue());
    }

    public boolean isVoided() {
        return voided;
    }

    public void setVoided(final boolean voided) {
        this.voided = voided;
    }
    
      /**
     * This method returns the editStatus of an observation
     * @return editStatus
     */
    public HashMap<String,String> getEditStatus(){
        return this.editStatus;
    }
}

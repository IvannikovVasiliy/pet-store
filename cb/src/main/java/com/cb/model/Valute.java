package com.cb.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Valute {
    @XmlElement(name = "NumCode")
    public String numCode;
    @XmlElement(name = "CharCode")
    public String charCode;
    @XmlElement(name = "Nominal")
    public String nominal;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Value")
    public String value;
}

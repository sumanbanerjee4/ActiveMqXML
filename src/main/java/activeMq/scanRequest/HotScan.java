//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.23 at 10:06:51 PM IST 
//


package activeMq.scanRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProfileID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransRef" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ScanData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "profileID",
    "transRef",
    "scanData"
})
@XmlRootElement(name = "HotScan")
public class HotScan {

    @XmlElement(name = "ProfileID", required = true)
    protected String profileID;
    @XmlElement(name = "TransRef")
    protected int transRef;
    @XmlElement(name = "ScanData", required = true)
    protected String scanData;

    /**
     * Gets the value of the profileID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfileID() {
        return profileID;
    }

    /**
     * Sets the value of the profileID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfileID(String value) {
        this.profileID = value;
    }

    /**
     * Gets the value of the transRef property.
     * 
     */
    public int getTransRef() {
        return transRef;
    }

    /**
     * Sets the value of the transRef property.
     * 
     */
    public void setTransRef(int value) {
        this.transRef = value;
    }

    /**
     * Gets the value of the scanData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanData() {
        return scanData;
    }

    /**
     * Sets the value of the scanData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanData(String value) {
        this.scanData = value;
    }

}

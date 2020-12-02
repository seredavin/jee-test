#distance-calculator 
##endpoints
- GET /rest/city - List of all cities in the DB. (no parameters)
- GET /rest/calc - Calculate distance
    - Request:
         ```
        <xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">
              <xs:element name="CalcRequestXml">
                <xs:complexType>
                  <xs:sequence>
                    <xs:element type="xs:string" name="distanceType"/>
                    <xs:element type="xs:int" name="from"/>
                    <xs:element type="xs:int" name="to"/>
                  </xs:sequence>
                </xs:complexType>
              </xs:element>
            </xs:schema>
        ```
    - distanceType: crowflight / matrix / all
    - from: city ID
    - to: city ID
    
##Upload data to the DB
- Path - /upload
- Schema
    ```<xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">
         <xs:element name="CityDistanceXml">
           <xs:complexType>
             <xs:sequence>
               <xs:element name="city" maxOccurs="unbounded" minOccurs="0">
                 <xs:complexType>
                   <xs:sequence>
                     <xs:element type="xs:string" name="id"/>
                     <xs:element type="xs:string" name="name"/>
                     <xs:element type="xs:float" name="latitude"/>
                     <xs:element type="xs:float" name="longitude"/>
                   </xs:sequence>
                 </xs:complexType>
               </xs:element>
               <xs:element name="distance" maxOccurs="unbounded" minOccurs="0">
                 <xs:complexType>
                   <xs:sequence>
                     <xs:element type="xs:int" name="from"/>
                     <xs:element type="xs:int" name="to"/>
                     <xs:element type="xs:short" name="distance"/>
                   </xs:sequence>
                 </xs:complexType>
               </xs:element>
             </xs:sequence>
           </xs:complexType>
         </xs:element>
       </xs:schema>```
 ##Data source в WildFly 
  - java:/magenta/datasource/test-distance-calculator
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ode.bpel.obj;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Message variable type.
 */
public class OMessageVarType extends OVarType  implements Serializable{
	public static final long serialVersionUID = -1L;

	private static final String MESSAGETYPE = "messageType";

	private static final String PARTS = "parts";

	/** For doc-lit-like message types , the element type of the only part. */
	private static final String DOCLITTYPE = "docLitType";

	@JsonCreator
	public OMessageVarType(){}
	public OMessageVarType(OProcess owner, QName messageType,
			Collection<Part> parts) {
		super(owner);
		setParts(new LinkedHashMap<String, Part>());
		setMessageType(messageType);
		for (Iterator<Part> i = parts.iterator(); i.hasNext();) {
			Part part = i.next();
			getParts().put(part.getName(), part);
		}

		if ((parts.size() == 1 && parts.iterator().next().getType() instanceof OElementVarType))
			setDocLitType((OElementVarType) parts.iterator().next().getType());
		else
			setDocLitType(null);
	}

	@JsonIgnore
	public OElementVarType getDocLitType() {
		Object o = fieldContainer.get(DOCLITTYPE);
		return o == null ? null : (OElementVarType)o;
	}

	@JsonIgnore
	public QName getMessageType() {
		Object o = fieldContainer.get(MESSAGETYPE);
		return o == null ? null : (QName)o;
	}

	@SuppressWarnings("unchecked")
	@JsonIgnore
	public Map<String, Part> getParts() {
		Object o = fieldContainer.get(PARTS);
		return o == null ? null : (Map<String, Part>)o;
	}

	@JsonIgnore
	boolean isDocLit() {
		return getDocLitType() != null;
	}

	public Node newInstance(Document doc) {
		Element el = doc.createElementNS(null, "message");
		for (OMessageVarType.Part part : getParts().values()) {
			Element partElement = doc.createElementNS(null, part.getName());
			partElement.appendChild(part.getType().newInstance(doc));
			el.appendChild(partElement);
		}
		return el;
	}

	public void setDocLitType(OElementVarType docLitType) {
		if (getDocLitType() == null){
			fieldContainer.put(DOCLITTYPE, docLitType);
		}
	}

	public void setMessageType(QName messageType) {
		fieldContainer.put(MESSAGETYPE, messageType);
	}

	public void setParts(Map<String, Part> parts) {
		if (getParts() == null){
			fieldContainer.put(PARTS, parts);
		}
	}

	public String toString() {
		StringBuffer buf = new StringBuffer(super.toString());
		buf.append('(');
		buf.append(getMessageType().toString());
		buf.append(')');
		return buf.toString();
	}

	public static class Part extends OBase  implements Serializable{
	public static final long serialVersionUID = -1L;

		private static final String NAME = "name";
		private static final String TYPE = "type";

		@JsonCreator
		public Part(){}
		public Part(OProcess owner, String partName, OVarType partType) {
			super(owner);
			setName(partName);
			setType(partType);
		}

		@JsonIgnore
		public String getName() {
			Object o = fieldContainer.get(NAME);
		return o == null ? null : (String)o;
		}

		@JsonIgnore
		public OVarType getType() {
			Object o = fieldContainer.get(TYPE);
		return o == null ? null : (OVarType)o;
		}

		public void setName(String name) {
			fieldContainer.put(NAME, name);
		}

		public void setType(OVarType type) {
			fieldContainer.put(TYPE, type);
		}

	}

}

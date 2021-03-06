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
package org.apache.ode.bpel.elang.xpath10.obj;

import java.io.Serializable;
import javax.xml.namespace.QName;

import org.apache.ode.bpel.elang.xpath10.obj.OXPath10Expression;
import org.apache.ode.bpel.obj.OProcess;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Jaxen-based compiled-xpath representation for XPATH 1.0 expression language.
 */
public class OXPath10ExpressionBPEL20 extends OXPath10Expression implements Serializable{
	private static final long serialVersionUID = -1L;

	/** QName of the <code>bpws:getVariableData</code> function. */
	private static final String QNAME_DOXSLTRANSFORM = "qname_doXslTransform";

	/** Flags this expression as a joinCondition */
	private static final String ISJOINEXPRESSION = "isJoinExpression";

	@JsonCreator
	public OXPath10ExpressionBPEL20(){
		setIsJoinExpression(false);
	}
	public OXPath10ExpressionBPEL20(OProcess owner,
			QName qname_getVariableData, QName qname_getVariableProperty,
			QName qname_getLinkStatus, QName qname_doXslTransform,
			boolean isJoinExpression) {
		super(owner, qname_getVariableData, qname_getVariableProperty,
				qname_getLinkStatus);
		setQname_doXslTransform(qname_doXslTransform);
		setIsJoinExpression(isJoinExpression);
	}

	@JsonIgnore
	public boolean isIsJoinExpression() {
		Object o = fieldContainer.get(ISJOINEXPRESSION);
		return o == null ? false : (Boolean)o;
	}

	@JsonIgnore
	public QName getQname_doXslTransform() {
		Object o = fieldContainer.get(QNAME_DOXSLTRANSFORM);
		return o == null ? null : (QName)o;
	}

	public void setIsJoinExpression(boolean isJoinExpression) {
		fieldContainer.put(ISJOINEXPRESSION, isJoinExpression);
	}

	public void setQname_doXslTransform(QName qname_doXslTransform) {
		fieldContainer.put(QNAME_DOXSLTRANSFORM, qname_doXslTransform);
	}

	public String toString() {
		return "{OXPath10Expression " + getXpath() + "}";
	}
}

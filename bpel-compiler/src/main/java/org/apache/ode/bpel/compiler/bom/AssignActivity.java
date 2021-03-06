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
package org.apache.ode.bpel.compiler.bom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

/**
 * BOM representation of the BPEL <code>&lt;assign&gt;</code> activity. The
 * <code>&lt;assign&gt;</code> activity is simply a collection of
 * <code>&lt;copy&gt;</code> entries ({@link Copy}).
 * 
 * @author Ode team
 * @author Tammo van Lessen (University of Stuttgart)
 */
public class AssignActivity extends Activity {

    public AssignActivity(Element el) {
        super(el);
    }

    /**
     * Get the list of <code>&lt;copy&gt;</code> entries for this activity.
     *
     * @return copy entries
     */
    public List<Copy> getCopies() {
        return getChildren(Copy.class);
    }

    /**
     * Get the list of <code>&lt;extensionAssignOperation&gt;</code> entries for this activity.
     * 
     * @return extensionAssignOperation entries
     */
    public List<ExtensionAssignOperation> getExtensionAssignOperations() {
        return getChildren(ExtensionAssignOperation.class);
    }

    /**
     * Get the list of all assign operation entries (<code>copy</code> and
     * <code>extensionAssignOperation</code>) for this activity.
     * 
     * @return assign operation entries
     */
    public List<AssignOperation> getOperations() {
        // all children objects
        List<BpelObject> children = getChildren(BpelObject.class);

        // aggregate only copy and extensionAssignOperation entries
        List<AssignOperation> ops = new ArrayList<AssignOperation>();
        for (BpelObject bo : children) {
            if ((bo instanceof Copy) || (bo instanceof ExtensionAssignOperation)) {
                ops.add((AssignOperation) bo);
            }
        }

        return ops;
    }

    /** Marker interface for assign operations */
    public static interface AssignOperation {}
}

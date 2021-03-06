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
package org.apache.ode.bpel.clapi;

import java.io.Serializable;

public abstract class ProcessStoreClusterEvent implements Serializable {
    protected static final long serialVersionUID = 1L;

    private String deploymentUnit;

    protected String info ;

    /** Unique ID of the Node in the Cluster generating the Event */
    private String eventGeneratingNode;

    public ProcessStoreClusterEvent(String deploymentUnit) {
        this.deploymentUnit = deploymentUnit;
    }

    @Override
    public String toString() {
        return "{ProcessStoreClusterEvent#" + deploymentUnit +"}";
    }

    public void setEventGeneratingNode(String uuid) {
        this.eventGeneratingNode = uuid;
    }

    public String getEventGeneratingNode() {
        return eventGeneratingNode;
    }

    public String getDuName() {
        return deploymentUnit;
    }

    public String getInfo() {
        return info;
    }

}

/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acegisecurity.acl;

import java.io.Serializable;


/**
 * Marker interface representing an access control list entry associated with a
 * specific domain object instance.
 *
 * @author Ben Alex
 * @version $Id: AclEntry.java 1784 2007-02-24 21:00:24Z luke_t $
 */
public interface AclEntry extends Serializable {}

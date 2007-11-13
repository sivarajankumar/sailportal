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

package org.acegisecurity.providers.cas;

import org.acegisecurity.AuthenticationException;


/**
 * Validates a CAS service ticket.
 *
 * <p>
 * Implementations must accept CAS proxy tickets, in addition to CAS service
 * tickets. If proxy tickets should be rejected, this is resolved by a {@link
 * CasProxyDecider} implementation (not by the <code>TicketValidator</code>).
 * </p>
 *
 * <p>
 * Implementations may request a proxy granting ticket if wish,  although this
 * behaviour is not mandatory.
 * </p>
 *
 * @author Ben Alex
 * @version $Id: TicketValidator.java 1784 2007-02-24 21:00:24Z luke_t $
 */
public interface TicketValidator {
    //~ Methods ========================================================================================================

    /**
     * Returns information about the ticket, if it is valid for this service.<P>Must throw an
     * <code>AuthenticationException</code> if the ticket is not valid for this service.</p>
     *
     * @param serviceTicket DOCUMENT ME!
     *
     * @return details of the CAS service ticket
     *
     * @throws AuthenticationException DOCUMENT ME!
     */
    TicketResponse confirmTicketValid(String serviceTicket)
        throws AuthenticationException;
}

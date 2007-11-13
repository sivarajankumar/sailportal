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

package org.acegisecurity;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;


/**
 * A property editor that can create a populated  {@link ConfigAttributeDefinition} from a comma separated list of
 * values.<P>Trims preceding and trailing spaces from presented command separated tokens, as this can be a source
 * of hard-to-spot configuration issues for end users.</p>
 *
 * @author Ben Alex
 * @version $Id: ConfigAttributeEditor.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class ConfigAttributeEditor extends PropertyEditorSupport {
    //~ Methods ========================================================================================================

    public void setAsText(String s) throws IllegalArgumentException {
        if ((s == null) || "".equals(s)) {
            setValue(null);
        } else {
            String[] tokens = StringUtils.commaDelimitedListToStringArray(s);
            ConfigAttributeDefinition configDefinition = new ConfigAttributeDefinition();

            for (int i = 0; i < tokens.length; i++) {
                configDefinition.addConfigAttribute(new SecurityConfig(tokens[i].trim()));
            }

            setValue(configDefinition);
        }
    }
}

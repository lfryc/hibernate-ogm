/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * JBoss, Home of Professional Open Source
 * Copyright 2010-2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.hibernate.ogm.type.descriptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.type.descriptor.java.JavaTypeDescriptor;

/**
 * @author Emmanuel Bernard
 */
public class BasicGridExtractor<J> implements GridValueExtractor<J> {
	private static final Logger log = LoggerFactory.getLogger( BasicGridExtractor.class );
	private final GridTypeDescriptor gridTypeDescriptor;
	private final JavaTypeDescriptor<J> javaTypeDescriptor;

	public BasicGridExtractor( JavaTypeDescriptor<J> javaTypeDescriptor, GridTypeDescriptor gridTypeDescriptor ) {
		this.gridTypeDescriptor = gridTypeDescriptor;
		this.javaTypeDescriptor = javaTypeDescriptor;
	}

	@Override
	public J extract(Map<String, Object> resultset, String name) {
		@SuppressWarnings( "unchecked" )
		final J result = (J) resultset.get( name );
		if ( result == null ) {
			log.trace( "found [null] as column [{}]", name );
			return null;
		}
		else {
			log.trace( "found [{}] as column [{}]", javaTypeDescriptor.extractLoggableRepresentation( result ), name );
			return result;
		}
	}
}

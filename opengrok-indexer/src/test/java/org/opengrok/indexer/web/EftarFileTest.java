/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2007, 2020, Oracle and/or its affiliates. All rights reserved.
 */
package org.opengrok.indexer.web;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test to test the EftarFile-system.
 */
public class EftarFileTest {

    private static File eftar;

    public EftarFileTest() {
    }

    private static final String PATH_STRING = "/path";

    @BeforeClass
    public static void setUpClass() throws Exception {

        eftar = File.createTempFile("paths", ".eftar");
        int len = 100;
        Set<PathDescription> descriptions = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (int ii = 0; ii < len; ii++) {
            sb.append(PATH_STRING);
            sb.append(ii);
            descriptions.add(new PathDescription(sb.toString(), "Description " + ii));
        }

        String outputFile = eftar.getAbsolutePath();

        EftarFile ef = new EftarFile();
        ef.create(descriptions, outputFile);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if (eftar != null) {
            eftar.delete();
        }
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test usage of an EftarFile.
     * @throws IOException if an error occurs while accessing the eftar file
     */
    @Test
    public void searchEftarFile() throws IOException {
        searchEftarFile(new EftarFileReader(eftar));
        searchEftarFile(new EftarFileReader(eftar.getAbsolutePath()));
    }

    private void searchEftarFile(EftarFileReader er) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringBuilder match = new StringBuilder();
        match.append("Description ");
        int offset = match.length();
        for (int ii = 0; ii < 100; ii++) {
            sb.append(PATH_STRING);
            sb.append(ii);
            match.setLength(offset);
            match.append(ii);

            assertEquals("description for path " + sb.toString(), match.toString(), er.get(sb.toString()));
        }
        er.close();
    }
}

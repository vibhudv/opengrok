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
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
 * Portions Copyright (c) 2017, 2019, Chris Fraire <cfraire@me.com>.
 */
package org.opengrok.indexer.analysis.fortran;

import org.junit.Test;
import org.opengrok.indexer.analysis.XrefTestBase;
import java.io.IOException;

import static org.opengrok.indexer.util.StreamUtils.readTagsFromResource;

/**
 * Tests the {@link FortranXref} class.
 */
public class FortranXrefTest extends XrefTestBase {

    @Test
    public void sampleTest() throws IOException {
        writeAndCompare(new FortranAnalyzerFactory(),
                "analysis/fortran/sample.f",
                "analysis/fortran/sample_xref.html",
                readTagsFromResource("analysis/fortran/sampletags"), 28);
    }

    @Test
    public void shouldCloseTruncatedStringSpan() throws IOException {
        writeAndCompare(new FortranAnalyzerFactory(),
                "analysis/fortran/truncated.f",
                "analysis/fortran/truncated_xref.html", null, 1);
    }
}

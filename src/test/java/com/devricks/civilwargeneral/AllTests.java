package com.devricks.civilwargeneral;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * JUnit 5 suite that discovers and runs all tests under the
 * com.devricks.civilwargeneral package (including subpackages).
 *
 * This enables running all project tests by invoking the test runner
 * on the directory: src/test/java/com/devricks/civilwargeneral
 */
@Suite
@SelectPackages("com.devricks.civilwargeneral")
@IncludeClassNamePatterns({".*Test"})
public class AllTests {
}

package com.thrivent;

import org.junit.platform.suite.api.ExcludeClassNamePatterns;
import org.junit.platform.suite.api.Suite;

@Suite(failIfNoTests = false)
@ExcludeClassNamePatterns({"^.*IT?$"})
public class UnitTestSuite {
}
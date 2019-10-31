package org.galatea.starter;

import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses("**/*Test.class")
@ExcludeCategories({IntegrationTestCategory.class})
public class UnitTestRunner {

}

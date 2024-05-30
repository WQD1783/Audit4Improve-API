package us.muit.fs.a4i.test.control;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import us.muit.fs.a4i.control.RepositoryIndicatorStrategy;
import us.muit.fs.a4i.exceptions.NotAvailableMetricException;

public class RepositoryIndicatorStrategyTest {
    // MARK: - Attributes
    static RepositoryIndicatorStrategy repositoryIndicatorStrategy;

    // MARK: - Setup methods
    /**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
        repositoryIndicatorStrategy = new RepositoryIndicatorStrategy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

    // MARK: - Test methods
    // Test the calculation of the indicator output
    @Test
    public void testCalcIndicator() throws NotAvailableMetricException {
        // TODO Implement test for the calculation of the indicator output
    }

    // Test the required metrics for the indicator calculation
    @Test
    public void testRequiredMetrics() {
        List<String> expectedRequirements = Arrays.asList("queryCommits", "issues", "branches", "pullRequests");

        for (String requirement : expectedRequirements) {
            Assertions.assertTrue(repositoryIndicatorStrategy.requiredMetrics().contains(requirement),
                    "The required metrics for RepositoryIndicatorStrategyTest did not contain the following expected metric: " + requirement);
        }
    }
}

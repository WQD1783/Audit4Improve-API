package us.muit.fs.a4i.test.control;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHIssue;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;

import us.muit.fs.a4i.control.RepositoryIndicatorStrategy;
import us.muit.fs.a4i.exceptions.NotAvailableMetricException;
import us.muit.fs.a4i.model.entities.ReportItemI;

public class RepositoryIndicatorStrategyTest {
    // MARK: - Attributes
    // The indicator strategy to be tested
    static RepositoryIndicatorStrategy repositoryIndicatorStrategy;
    // Mocks
    @Mock
    static ReportItemI<Double> mockConventionalCommits;
    @Mock
    static ReportItemI<Double> mockCommitsWithDescription;
    @Mock
    static ReportItemI<Double> mockIssuesWithLabels;
    @Mock
    static ReportItemI<Double> mockGitFlowBranches;
    @Mock
    static ReportItemI<Double> mockConventionalPullRequests;
    // The expected calculation of the indicator
    static Double calculation;

    // MARK: - Setup methods
    /**
	 * @throws java.lang.Exception
	 */
    @SuppressWarnings("unchecked")
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
        // Create the indicator strategy, here we use the default weights
        repositoryIndicatorStrategy = new RepositoryIndicatorStrategy();

        // Mock the required metrics
        mockConventionalCommits = Mockito.mock(ReportItemI.class);
        mockCommitsWithDescription = Mockito.mock(ReportItemI.class);
        mockIssuesWithLabels = Mockito.mock(ReportItemI.class);
        mockGitFlowBranches = Mockito.mock(ReportItemI.class);
        mockConventionalPullRequests = Mockito.mock(ReportItemI.class);

        // Mock the metric values
        when(mockConventionalCommits.getValue()).thenReturn(0.86);
        when(mockCommitsWithDescription.getValue()).thenReturn(0.84);
        when(mockIssuesWithLabels.getValue()).thenReturn(0.92);
        when(mockGitFlowBranches.getValue()).thenReturn(0.85);
        when(mockConventionalPullRequests.getValue()).thenReturn(0.98);

        // Based on the provided metrics, the indicator should be calculated as follows:
        calculation = (0.2 * 0.86) + (0.2 * 0) + (0.2 * 0.92) + (0.2 * 0) + (0.2 * 0.98);

        // Mock the metric names
        when(mockConventionalCommits.getName()).thenReturn("conventionalCommits");
        when(mockCommitsWithDescription.getName()).thenReturn("commitsWithDescription");
        when(mockIssuesWithLabels.getName()).thenReturn("issuesWithLabels");
        when(mockGitFlowBranches.getName()).thenReturn("gitFlowBranches");
        when(mockConventionalPullRequests.getName()).thenReturn("conventionalPullRequests");
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
    /**
     * The indicator is calculated based on the following metrics:
        * Metric 1: Ratio of commits complying with summary naming conventions (conventional commits). Must be above 85%.

        * Metric 2: Ratio of commits that have both a summary and a description. Must be above 85%.
        * Metric 3: Ratio of issues that have at least one label, compared to the total amount of issues. Must be above 85%.
        * Metric 4: The ratio of all branches that follow the correct naming conventions. Must be a 100% compliance.
        * Metric 5: Ratio of pull requests following correct naming and description conventions. Must be above 85%.
        * 
        * Where RepoIndicator = w1 * C1 + w2 * C2 + w3 * C3 + w4 * C4 + w5 * C5, and:
        * C_i = M_i if M_i >= 0.85 else 0 when i in {1, 2, 3, 5}
        * C_4 = 1 if M_4 == 1 else 0
     * @throws NotAvailableMetricException
     */
    @Test
    public void testCalcIndicator() throws NotAvailableMetricException {
        // Set up the list of metrics
        List<ReportItemI<Double>> metrics = Arrays.asList(
            mockConventionalCommits,
            mockCommitsWithDescription,
            mockIssuesWithLabels,
            mockGitFlowBranches,
            mockConventionalPullRequests
        );

        // Calculate the indicator output
        ReportItemI<Double> result = repositoryIndicatorStrategy.calcIndicator(metrics);

        // Check the result
        Assertions.assertEquals("RepositoryIndicatorStrategy", result.getName(), "The indicator name is not correct.");
        Assertions.assertEquals(calculation, result.getValue(), "The indicator value from the calculation is not correct.");
    }

    /**
     * Test the calculation of the indicator when the required metrics are not available
     */
    @Test
    public void testCalcIndicatorThrowsNotAvailableMetricException() {
        Random random = new Random();
        // Set up the list of metrics
        List<ReportItemI<Double>> metrics = Arrays.asList(
            mockConventionalCommits,
            mockCommitsWithDescription,
            mockIssuesWithLabels,
            mockGitFlowBranches,
            mockConventionalPullRequests
        );
        // Remove one of the required metrics
        metrics.remove(random.nextInt(metrics.size()));

        // Check that the method throws the expected exception
        Assertions.assertThrows(NotAvailableMetricException.class, () -> {
            repositoryIndicatorStrategy.calcIndicator(metrics);
        });
    }

    /**
     * Test the required metrics for the indicator calculation
     */
    @Test
    public void testRequiredMetrics() {
        List<String> expectedRequirements = Arrays.asList("queryCommits", "issues", "branches", "pullRequests");

        for (String requirement : expectedRequirements) {
            Assertions.assertTrue(repositoryIndicatorStrategy.requiredMetrics().contains(requirement),
                    "The required metrics for RepositoryIndicatorStrategyTest did not contain the following expected metric: " + requirement);
        }
    }
}

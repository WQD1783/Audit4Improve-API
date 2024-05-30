package us.muit.fs.a4i.control;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import us.muit.fs.a4i.exceptions.NotAvailableMetricException;
import us.muit.fs.a4i.model.entities.Indicator;
import us.muit.fs.a4i.model.entities.ReportItemI;

public class RepositoryIndicatorStrategy implements IndicatorStrategy<Double> {
    // MARK: - Attributes
    private static Logger log = Logger.getLogger(Indicator.class.getName());
    // The metrics needed to calculate the indicator output
    private static final List<String> REQUIRED_METRICS = Arrays.asList(
        "conventionalCommits", 
        "commitsWithDescription", 
        "issuesWithLabels", 
        "gitFlowBranches", 
        "conventionalPullRequests");
    private static final String id = "RepositoryIndicatorStrategy";

    // Weights for each metric
    private static double WEIGHT_CONVENTIONAL_COMMITS = 0.2;
    private static double WEIGHT_COMMITS_WITH_DESCRIPTION = 0.2;
    private static double WEIGHT_ISSUES_WITH_LABELS = 0.2;
    private static double WEIGHT_GIT_FLOW_BRANCHES = 0.2;
    private static double WEIGHT_CONVENTIONAL_PULL_REQUESTS = 0.2;

    // MARK: - Constructor
    /**
     * Constructor. Default weights are equal for all metrics (0.2).
     */
    public RepositoryIndicatorStrategy() {
        super();
    }
    /**
     * Constructor. Set the weights for each metric. The sum of the weights must be equal to 1.
     * @param weightConventionalCommits the weight for the conventional commits metric
     * @param weightCommitsWithDescription the weight for the commits with description metric
     * @param weightIssuesWithLabels the weight for the issues with labels metric
     * @param weightGitFlowBranches the weight for the git flow branches metric
     * @param weightConventionalPullRequests the weight for the conventional pull requests metric
     */
    public RepositoryIndicatorStrategy(Double weightConventionalCommits, Double weightCommitsWithDescription, 
        Double weightIssuesWithLabels, Double weightGitFlowBranches, 
        Double weightConventionalPullRequests) throws IllegalArgumentException {
        // Control that the sum of the weights is equal to 1
        if (weightConventionalCommits + weightCommitsWithDescription + weightIssuesWithLabels + weightGitFlowBranches + weightConventionalPullRequests != 1) {
            throw new IllegalArgumentException("The sum of the weights must be equal to 1");
        }
        
        // Set the weights
        this.WEIGHT_CONVENTIONAL_COMMITS = weightConventionalCommits;
        this.WEIGHT_COMMITS_WITH_DESCRIPTION = weightCommitsWithDescription;
        this.WEIGHT_ISSUES_WITH_LABELS = weightIssuesWithLabels;
        this.WEIGHT_GIT_FLOW_BRANCHES = weightGitFlowBranches;
        this.WEIGHT_CONVENTIONAL_PULL_REQUESTS = weightConventionalPullRequests;
    }

    // MARK: - Implemented methods
    // Calculate the indicator output based on the provided metrics
    @Override
    public ReportItemI<Double> calcIndicator(List<ReportItemI<Double>> metrics) throws NotAvailableMetricException {
        // TODO Develop logic for calculating the indicator output
        /*
         * The indicator is calculated based on the following metrics:
         * Metric 1: Ratio of commits complying with summary naming conventions (conventional commits). Must be above 85%.
         * Metric 2: Ratio of commits that have both a summary and a description. Must be above 85%.
         * Metric 3: Ratio of issues that have at least one label, compared to the total amount of issues. Must be above 85%.
         * Metric 4: The ratio of all branches that follow the correct naming conventions. Must be a 100% compliance.
         * Metric 5: Ratio of pull requests following correct naming and description conventions. Must be above 85%.
         */

        throw new UnsupportedOperationException("Unimplemented method 'calcIndicator'");
    }

    // Return the metrics required to calculate the indicator output
    @Override
    public List<String> requiredMetrics() {
        return REQUIRED_METRICS;
    }
    
}

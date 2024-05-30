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
    private static final List<String> REQUIRED_METRICS = Arrays.asList("queryCommits", "issues", "branches", "pullRequests");

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

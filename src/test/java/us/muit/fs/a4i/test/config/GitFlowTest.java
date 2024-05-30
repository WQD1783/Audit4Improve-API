package us.muit.fs.a4i.test.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import us.muit.fs.a4i.config.GitFlow;

public class GitFlowTest {
    @Test
    void testToString() {
        // Test the toString method
        assertEquals("main", GitFlow.MAIN.toString());
        assertEquals("develop", GitFlow.DEVELOP.toString());
        assertEquals("feature", GitFlow.FEATURE.toString());
        assertEquals("release", GitFlow.RELEASE.toString());
        assertEquals("hotfix", GitFlow.HOTFIX.toString());
    }

    @Test
    void testIsGitFlowBranch() {
        // Test the isGitFlowBranch method
        assertTrue(GitFlow.isGitFlowBranch("main"));
        assertTrue(GitFlow.isGitFlowBranch("develop"));
        assertTrue(GitFlow.isGitFlowBranch("feature/featureName"));
        assertTrue(GitFlow.isGitFlowBranch("release/releaseName"));
        assertTrue(GitFlow.isGitFlowBranch("hotfix/hotfixName"));
        assertFalse(GitFlow.isGitFlowBranch("feature"));
        assertFalse(GitFlow.isGitFlowBranch("release"));
        assertFalse(GitFlow.isGitFlowBranch("hotfix"));
        assertFalse(GitFlow.isGitFlowBranch("main/featureName"));
        assertFalse(GitFlow.isGitFlowBranch("develop/featureName"));
    }
}

package org.cyclonedx.maven;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.takari.maven.testing.TestResources;
import io.takari.maven.testing.executor.MavenRuntime;
import io.takari.maven.testing.executor.MavenRuntime.MavenRuntimeBuilder;
import io.takari.maven.testing.executor.MavenVersions;
import io.takari.maven.testing.executor.junit.MavenJUnitTestRunner;

@RunWith(MavenJUnitTestRunner.class)
@MavenVersions({"3.6.3"})
public class Issue111MakeAggregateRegressionInstallTest {

    @Rule
    public final TestResources resources = new TestResources(
            "target/test-classes",
            "target/test-classes/transformed-projects"
    );

    public final MavenRuntime verifier;

    public Issue111MakeAggregateRegressionInstallTest(MavenRuntimeBuilder runtimeBuilder)
            throws Exception {
        this.verifier = runtimeBuilder.build();
    }

    @Test
    public void makeAggregateFails() throws Exception {
        File projectDirTransformed = new File(
                "target/test-classes/transformed-projects/issue-111"
        );
        if (projectDirTransformed.exists()) {
            FileUtils.cleanDirectory(projectDirTransformed);
            projectDirTransformed.delete();
        }

        File projDir = resources.getBasedir("issue-111");

        Properties props = new Properties();

        props.load(Issue111MakeAggregateRegressionInstallTest.class.getClassLoader().getResourceAsStream("test.properties"));
        String projectVersion = String.class.cast(props.get("project.version"));
        verifier
                .forProject(projDir) //
                .withCliOption("-Dtest.input.version=" + projectVersion) // debug
                .withCliOption("-e")
                .withCliOption("-B")
                .execute("clean", "install")
                .assertErrorFreeLog();
    }



}

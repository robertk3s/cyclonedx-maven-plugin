package org.cyclonedx.maven;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;

import com.soebes.itf.jupiter.extension.MavenCLIOptions;
import com.soebes.itf.jupiter.extension.MavenGoal;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenOption;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;

@MavenJupiterExtension
public class Issue111TestIT
{

    @MavenTest
    @MavenGoal({"clean", "install"})
    @MavenOption(MavenCLIOptions.NO_TRANSFER_PROGRESS)
    void test_makeAggregate_111(MavenExecutionResult result) {
     assertThat(result).isSuccessful();
    }

    @MavenTest
    @MavenGoal({"clean", "install"})
    @MavenOption(MavenCLIOptions.NO_TRANSFER_PROGRESS)
    void test_makeAggregate_fails(MavenExecutionResult result) {
     assertThat(result).isSuccessful();
    }

}

package hmf.testsupport;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import hmf.io.PipelineOutput;
import hmf.sample.FlowCell;
import htsjdk.samtools.SAMFlag;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SamReader;

class DedupedFileAssertion extends BAMFileAssertion {

    DedupedFileAssertion(final FlowCell cell) {
        super(PipelineOutput.DEDUPED, cell);
    }

    @Override
    void assertFile(final SamReader expected, final SamReader results) {
        List<String> duplicatesExpected = findDuplicates(expected);
        List<String> duplicatesResults = findDuplicates(results);
        assertThat(duplicatesResults).containsExactlyInAnyOrder(duplicatesExpected.toArray(new String[duplicatesExpected.size()]));
    }

    private List<String> findDuplicates(final SamReader samReaderResults) {
        List<String> duplicates = new ArrayList<>();
        for (SAMRecord record : samReaderResults) {
            if (SAMFlag.getFlags(record.getFlags()).contains(SAMFlag.DUPLICATE_READ)) {
                duplicates.add(record.getReadName());
            }
        }
        return duplicates;
    }
}

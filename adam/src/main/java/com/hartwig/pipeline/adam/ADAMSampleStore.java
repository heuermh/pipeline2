package com.hartwig.pipeline.adam;

import java.io.File;

import com.hartwig.io.Output;
import com.hartwig.io.OutputFile;
import com.hartwig.io.OutputStore;
import com.hartwig.io.OutputType;
import com.hartwig.patient.Sample;

import org.bdgenomics.adam.rdd.read.AlignmentRecordRDD;

public class ADAMSampleStore implements OutputStore<Sample, AlignmentRecordRDD> {

    @Override
    public void store(final Output<Sample, AlignmentRecordRDD> output) {
        output.payload().save(Persistence.defaultSave(output.entity(), output.type()), true);
    }

    @Override
    public boolean exists(final Sample entity, final OutputType type) {
        return new File(OutputFile.of(type, entity).path()).exists();
    }
}
package com.hartwig.pipeline;

import java.io.IOException;

import com.hartwig.io.Output;
import com.hartwig.io.OutputType;
import com.hartwig.patient.FileSystemEntity;

public interface Stage<T extends FileSystemEntity, P> {

    OutputType outputType();

    Output<T, P> execute(T input) throws IOException;
}
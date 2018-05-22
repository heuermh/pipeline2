package hmf.pipeline.adam;

import java.io.IOException;

import org.bdgenomics.adam.api.java.JavaADAMContext;
import org.bdgenomics.adam.rdd.ADAMSaveAnyArgs;

import hmf.pipeline.PipelineOutput;
import hmf.pipeline.Stage;
import hmf.sample.Lane;

class CoordinateSortADAM implements Stage<Lane> {

    private final JavaADAMContext javaADAMContext;

    CoordinateSortADAM(final JavaADAMContext javaADAMContext) {
        this.javaADAMContext = javaADAMContext;
    }

    @Override
    public PipelineOutput output() {
        return PipelineOutput.SORTED;
    }

    @Override
    public void execute(Lane lane) throws IOException {
        ADAMSaveAnyArgs args = SaveArgs.defaultSave(lane, output());
        javaADAMContext.loadAlignments(PipelineOutput.ALIGNED.path(lane)).save(args, true);
    }
}

package dk.rim.is.ic.inttests.nets;

import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileEntity;
import dk.rim.is.common.entity.managedfiletransfer.IntegrationFileTypeEntity;
import dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileTypeName;
import dk.rim.is.ic.inttests.util.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStatus.GENERATED_BY_PSRM;
import static dk.rim.is.common.entity.managedfiletransfer.enums.IntegrationFileStep.EXPORT;

/**
 * Created by mlo on 17-02-2017.
 */
@Component
public class NetsUtil {
    @Autowired
    private GenericDao<IntegrationFileEntity> fileDao;

    @Autowired
    private GenericDao<IntegrationFileTypeEntity> fileTypeDao;

    public IntegrationFileEntity insertTestIntegrationFile(String fileData, IntegrationFileTypeName type) throws IOException {
        IntegrationFileTypeEntity fileType = fileTypeDao.getBy(IntegrationFileTypeEntity.class, entity -> type.equals(entity.getFileTypeName())).get(0);

        IntegrationFileEntity file = new IntegrationFileEntity();
        file.setUuid(UUID.randomUUID().toString());
        file.setIntegrationFileType(fileType);

        file.setRawFileContents(fileData.getBytes("UTF-8"));

        String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + type.toString() +"_testFileName.xml";

        file.setFileName(filename);
        file.setStep(EXPORT);
        file.setStatus(GENERATED_BY_PSRM);

        file.setStatusBy("BATCH_TEST");
        file.setCreatedBy("BATCH_TEST");

        Date testDate = new Date();
        file.setStatusTime(testDate);
        file.setFileGeneratedTime(testDate);
        file.setCreatedTime(testDate);

        fileDao.save(file);

        return file;
    }
}

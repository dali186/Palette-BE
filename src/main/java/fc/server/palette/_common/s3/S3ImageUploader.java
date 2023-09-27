package fc.server.palette._common.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class S3ImageUploader {
    @Value("${s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    //컨트롤러로 부터 이미지를 받는 메서드

    /**
     * @param directory s3 내 디렉토릭 될 각 도메인의 이름 ex)purchase
     * @param images    요청으로 받은 이미지 리스트
     * @return 이미지의 저장경로 즉, URL이 담긴 리스트
     * @throws IOException
     */
    public List<String> save(String directory, List<MultipartFile> images) throws IOException {
        List<String> paths = new ArrayList<>();

        for (MultipartFile image : images) {
            InputStream inputStream = image.getInputStream();
            String path = directory + "/" + image.getOriginalFilename();

            paths.add(path);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(path)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));

            inputStream.close();
        }

        return paths;
    }
}


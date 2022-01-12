package leandro.image.api.config


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client


@Configuration
class AwsConfiguration(
    @Value("\${aws.credentials.access-key}")
    private val awsAccessKey: String,

    @Value("\${aws.credentials.secret-key}")
    private val awsSecretKey: String,

    @Value("\${aws.region}")
    private val awsRegion: String

) {


    @Bean
    fun amazonS3(): S3Client {

        val credentials = AwsBasicCredentials.create(
            awsAccessKey,
            awsSecretKey
        )
        val credentialsProvider: AwsCredentialsProvider = StaticCredentialsProvider.create(credentials)

        return S3Client.builder()
            .credentialsProvider(credentialsProvider)
            .region(Region.of(awsRegion))
            .build();
    }
}
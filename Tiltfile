# Build
#docker_build('order-service', '.')
custom_build(
    ref = 'order-service',
    command = 'gradlew bootBuildImage --imageName %EXPECTED_REF%',
    deps = ['build.gradle', 'src']
)

# Deploy
k8s_yaml(['k8s/deployment.yml'])


# Manage
k8s_resource('order-service', port_forwards=['9002'])
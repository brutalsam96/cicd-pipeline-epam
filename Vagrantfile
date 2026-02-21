Vagrant.configure("2") do |config|
  config.vm.box = "generic/alma9"

  config.vm.network "forwarded_port", guest: 8080, host: 8080

  config.vm.provision "shell", inline: <<-SHELL
    sudo dnf install -y java-17-openjdk fontconfig wget

    sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo

    sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2026.key

    sudo dnf install -y jenkins
    sudo systemctl enable jenkins
    sudo systemctl start jenkins

    echo "--------------------------------------------------"
    echo "Jenkins is ready at http://localhost:8080"
    echo "Initial Admin Password:"
    sudo cat /var/lib/jenkins/secrets/initialAdminPassword
    echo "--------------------------------------------------"
  SHELL
end

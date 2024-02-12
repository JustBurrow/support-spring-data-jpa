package kr.lul.support.spring.data.jpa;

public abstract class SupportSpringDataJpaModuleAnchor {
    public static final Package PACKAGE = SupportSpringDataJpaModuleAnchor.class.getPackage();
    public static final String PACKAGE_NAME = PACKAGE.getName();

    protected SupportSpringDataJpaModuleAnchor() {
        throw new UnsupportedOperationException();
    }
}

package findus_pageobjects;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GenericInstantiatorTests {

    @Test(groups = "unit")
    public void ValidGenericTypeParameterAndConstructor_ShouldReturnCorrectInstance() {
        GenericInstantiator<ValidGenericTypeParameter> instantiator = new GenericInstantiator<ValidGenericTypeParameter>(
                ValidGenericTypeParameter.class) {
        };

        ValidGenericTypeParameter instantiatedObject = instantiator.create();

        Assert.assertEquals(instantiatedObject.getField(), "non_arg_ctor");
    }

    @Test(groups = "unit", expectedExceptions = IllegalArgumentException.class)
    public void InvalidAbstractGenericType_ShouldThrow() {
        GenericInstantiator<InvalidAbstractGenericTypeParameter> instantiator = new GenericInstantiator<InvalidAbstractGenericTypeParameter>(
                InvalidAbstractGenericTypeParameter.class) {
        };
    }

    @Test(groups = "unit", expectedExceptions = IllegalArgumentException.class)
    public void InvalidPrivateConstructorGenericType_ShouldThrow() {
        GenericInstantiator<InvalidPrivateConstructorGenericTypeParameter> instantiator = new GenericInstantiator<InvalidPrivateConstructorGenericTypeParameter>(
                InvalidPrivateConstructorGenericTypeParameter.class) {
        };
    }

    public static class ValidGenericTypeParameter {
        private String field;

        public String getField() {
            return this.field;
        }

        public ValidGenericTypeParameter() {
            this.field = "non_arg_ctor";
        }

        public ValidGenericTypeParameter(String field) {
            this.field = field;
        }
    }

    public static abstract class InvalidAbstractGenericTypeParameter {
        private String field;

        public String getField() {
            return this.field;
        }

        private InvalidAbstractGenericTypeParameter(String field) {
            this.field = field;
        }
    }

    public static class InvalidPrivateConstructorGenericTypeParameter {
        private String field;

        public String getField() {
            return this.field;
        }

        private InvalidPrivateConstructorGenericTypeParameter(String field) {
            this.field = field;
        }
    }
}

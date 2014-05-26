// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.web;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JCompany;
import com.j1987.coffeeroo.domain.JDealer;
import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JLocalization;
import com.j1987.coffeeroo.domain.JRole;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.domain.JTour;
import com.j1987.coffeeroo.domain.JUser;
import com.j1987.coffeeroo.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<JCoffeeAnalysis, String> ApplicationConversionServiceFactoryBean.getJCoffeeAnalysisToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JCoffeeAnalysis, java.lang.String>() {
            public String convert(JCoffeeAnalysis jCoffeeAnalysis) {
                return new StringBuilder().append(jCoffeeAnalysis.getReference()).append(' ').append(jCoffeeAnalysis.getDateOfAnalysis()).append(' ').append(jCoffeeAnalysis.getNumberLading()).append(' ').append(jCoffeeAnalysis.getTruckNumber()).toString();
            }
        };
    }
    
    public Converter<Long, JCoffeeAnalysis> ApplicationConversionServiceFactoryBean.getIdToJCoffeeAnalysisConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JCoffeeAnalysis>() {
            public com.j1987.coffeeroo.domain.JCoffeeAnalysis convert(java.lang.Long id) {
                return JCoffeeAnalysis.findJCoffeeAnalysis(id);
            }
        };
    }
    
    public Converter<String, JCoffeeAnalysis> ApplicationConversionServiceFactoryBean.getStringToJCoffeeAnalysisConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JCoffeeAnalysis>() {
            public com.j1987.coffeeroo.domain.JCoffeeAnalysis convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JCoffeeAnalysis.class);
            }
        };
    }
    
    public Converter<JCompany, String> ApplicationConversionServiceFactoryBean.getJCompanyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JCompany, java.lang.String>() {
            public String convert(JCompany jCompany) {
                return new StringBuilder().append(jCompany.getName()).append(' ').append(jCompany.getNumberCC()).append(' ').append(jCompany.getTaxationRegime()).append(' ').append(jCompany.getTaxCenter()).toString();
            }
        };
    }
    
    public Converter<Long, JCompany> ApplicationConversionServiceFactoryBean.getIdToJCompanyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JCompany>() {
            public com.j1987.coffeeroo.domain.JCompany convert(java.lang.Long id) {
                return JCompany.findJCompany(id);
            }
        };
    }
    
    public Converter<String, JCompany> ApplicationConversionServiceFactoryBean.getStringToJCompanyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JCompany>() {
            public com.j1987.coffeeroo.domain.JCompany convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JCompany.class);
            }
        };
    }
    
    public Converter<JDealer, String> ApplicationConversionServiceFactoryBean.getJDealerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JDealer, java.lang.String>() {
            public String convert(JDealer jDealer) {
                return new StringBuilder().append(jDealer.getCode()).append(' ').append(jDealer.getName()).append(' ').append(jDealer.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, JDealer> ApplicationConversionServiceFactoryBean.getIdToJDealerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JDealer>() {
            public com.j1987.coffeeroo.domain.JDealer convert(java.lang.Long id) {
                return JDealer.findJDealer(id);
            }
        };
    }
    
    public Converter<String, JDealer> ApplicationConversionServiceFactoryBean.getStringToJDealerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JDealer>() {
            public com.j1987.coffeeroo.domain.JDealer convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JDealer.class);
            }
        };
    }
    
    public Converter<JExporter, String> ApplicationConversionServiceFactoryBean.getJExporterToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JExporter, java.lang.String>() {
            public String convert(JExporter jExporter) {
                return new StringBuilder().append(jExporter.getCode()).append(' ').append(jExporter.getName()).append(' ').append(jExporter.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, JExporter> ApplicationConversionServiceFactoryBean.getIdToJExporterConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JExporter>() {
            public com.j1987.coffeeroo.domain.JExporter convert(java.lang.Long id) {
                return JExporter.findJExporter(id);
            }
        };
    }
    
    public Converter<String, JExporter> ApplicationConversionServiceFactoryBean.getStringToJExporterConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JExporter>() {
            public com.j1987.coffeeroo.domain.JExporter convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JExporter.class);
            }
        };
    }
    
    public Converter<JFactory, String> ApplicationConversionServiceFactoryBean.getJFactoryToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JFactory, java.lang.String>() {
            public String convert(JFactory jFactory) {
                return new StringBuilder().append(jFactory.getCode()).append(' ').append(jFactory.getName()).append(' ').append(jFactory.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, JFactory> ApplicationConversionServiceFactoryBean.getIdToJFactoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JFactory>() {
            public com.j1987.coffeeroo.domain.JFactory convert(java.lang.Long id) {
                return JFactory.findJFactory(id);
            }
        };
    }
    
    public Converter<String, JFactory> ApplicationConversionServiceFactoryBean.getStringToJFactoryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JFactory>() {
            public com.j1987.coffeeroo.domain.JFactory convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JFactory.class);
            }
        };
    }
    
    public Converter<JLocalization, String> ApplicationConversionServiceFactoryBean.getJLocalizationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JLocalization, java.lang.String>() {
            public String convert(JLocalization jLocalization) {
                return new StringBuilder().append(jLocalization.getName()).append(' ').append(jLocalization.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, JLocalization> ApplicationConversionServiceFactoryBean.getIdToJLocalizationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JLocalization>() {
            public com.j1987.coffeeroo.domain.JLocalization convert(java.lang.Long id) {
                return JLocalization.findJLocalization(id);
            }
        };
    }
    
    public Converter<String, JLocalization> ApplicationConversionServiceFactoryBean.getStringToJLocalizationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JLocalization>() {
            public com.j1987.coffeeroo.domain.JLocalization convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JLocalization.class);
            }
        };
    }
    
    public Converter<JRole, String> ApplicationConversionServiceFactoryBean.getJRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JRole, java.lang.String>() {
            public String convert(JRole jRole) {
                return new StringBuilder().append(jRole.getName()).append(' ').append(jRole.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, JRole> ApplicationConversionServiceFactoryBean.getIdToJRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JRole>() {
            public com.j1987.coffeeroo.domain.JRole convert(java.lang.Long id) {
                return JRole.findJRole(id);
            }
        };
    }
    
    public Converter<String, JRole> ApplicationConversionServiceFactoryBean.getStringToJRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JRole>() {
            public com.j1987.coffeeroo.domain.JRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JRole.class);
            }
        };
    }
    
    public Converter<JSubmissionForApproval, String> ApplicationConversionServiceFactoryBean.getJSubmissionForApprovalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JSubmissionForApproval, java.lang.String>() {
            public String convert(JSubmissionForApproval jSubmissionForApproval) {
                return new StringBuilder().append(jSubmissionForApproval.getReference()).append(' ').append(jSubmissionForApproval.getDescription()).append(' ').append(jSubmissionForApproval.getCreationDate()).append(' ').append(jSubmissionForApproval.getCreatedBy()).toString();
            }
        };
    }
    
    public Converter<Long, JSubmissionForApproval> ApplicationConversionServiceFactoryBean.getIdToJSubmissionForApprovalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JSubmissionForApproval>() {
            public com.j1987.coffeeroo.domain.JSubmissionForApproval convert(java.lang.Long id) {
                return JSubmissionForApproval.findJSubmissionForApproval(id);
            }
        };
    }
    
    public Converter<String, JSubmissionForApproval> ApplicationConversionServiceFactoryBean.getStringToJSubmissionForApprovalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JSubmissionForApproval>() {
            public com.j1987.coffeeroo.domain.JSubmissionForApproval convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JSubmissionForApproval.class);
            }
        };
    }
    
    public Converter<JSupplier, String> ApplicationConversionServiceFactoryBean.getJSupplierToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JSupplier, java.lang.String>() {
            public String convert(JSupplier jSupplier) {
                return new StringBuilder().append(jSupplier.getCode()).append(' ').append(jSupplier.getName()).append(' ').append(jSupplier.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, JSupplier> ApplicationConversionServiceFactoryBean.getIdToJSupplierConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JSupplier>() {
            public com.j1987.coffeeroo.domain.JSupplier convert(java.lang.Long id) {
                return JSupplier.findJSupplier(id);
            }
        };
    }
    
    public Converter<String, JSupplier> ApplicationConversionServiceFactoryBean.getStringToJSupplierConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JSupplier>() {
            public com.j1987.coffeeroo.domain.JSupplier convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JSupplier.class);
            }
        };
    }
    
    public Converter<JTour, String> ApplicationConversionServiceFactoryBean.getJTourToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JTour, java.lang.String>() {
            public String convert(JTour jTour) {
                return new StringBuilder().append(jTour.getName()).append(' ').append(jTour.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, JTour> ApplicationConversionServiceFactoryBean.getIdToJTourConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JTour>() {
            public com.j1987.coffeeroo.domain.JTour convert(java.lang.Long id) {
                return JTour.findJTour(id);
            }
        };
    }
    
    public Converter<String, JTour> ApplicationConversionServiceFactoryBean.getStringToJTourConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JTour>() {
            public com.j1987.coffeeroo.domain.JTour convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JTour.class);
            }
        };
    }
    
    public Converter<JUser, String> ApplicationConversionServiceFactoryBean.getJUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.j1987.coffeeroo.domain.JUser, java.lang.String>() {
            public String convert(JUser jUser) {
                return new StringBuilder().append(jUser.getUserName()).append(' ').append(jUser.getFirstName()).append(' ').append(jUser.getLastName()).append(' ').append(jUser.getPhoneNumber()).toString();
            }
        };
    }
    
    public Converter<Long, JUser> ApplicationConversionServiceFactoryBean.getIdToJUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.j1987.coffeeroo.domain.JUser>() {
            public com.j1987.coffeeroo.domain.JUser convert(java.lang.Long id) {
                return JUser.findJUser(id);
            }
        };
    }
    
    public Converter<String, JUser> ApplicationConversionServiceFactoryBean.getStringToJUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.j1987.coffeeroo.domain.JUser>() {
            public com.j1987.coffeeroo.domain.JUser convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), JUser.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getJCoffeeAnalysisToStringConverter());
        registry.addConverter(getIdToJCoffeeAnalysisConverter());
        registry.addConverter(getStringToJCoffeeAnalysisConverter());
        registry.addConverter(getJCompanyToStringConverter());
        registry.addConverter(getIdToJCompanyConverter());
        registry.addConverter(getStringToJCompanyConverter());
        registry.addConverter(getJDealerToStringConverter());
        registry.addConverter(getIdToJDealerConverter());
        registry.addConverter(getStringToJDealerConverter());
        registry.addConverter(getJExporterToStringConverter());
        registry.addConverter(getIdToJExporterConverter());
        registry.addConverter(getStringToJExporterConverter());
        registry.addConverter(getJFactoryToStringConverter());
        registry.addConverter(getIdToJFactoryConverter());
        registry.addConverter(getStringToJFactoryConverter());
        registry.addConverter(getJLocalizationToStringConverter());
        registry.addConverter(getIdToJLocalizationConverter());
        registry.addConverter(getStringToJLocalizationConverter());
        registry.addConverter(getJRoleToStringConverter());
        registry.addConverter(getIdToJRoleConverter());
        registry.addConverter(getStringToJRoleConverter());
        registry.addConverter(getJSubmissionForApprovalToStringConverter());
        registry.addConverter(getIdToJSubmissionForApprovalConverter());
        registry.addConverter(getStringToJSubmissionForApprovalConverter());
        registry.addConverter(getJSupplierToStringConverter());
        registry.addConverter(getIdToJSupplierConverter());
        registry.addConverter(getStringToJSupplierConverter());
        registry.addConverter(getJTourToStringConverter());
        registry.addConverter(getIdToJTourConverter());
        registry.addConverter(getStringToJTourConverter());
        registry.addConverter(getJUserToStringConverter());
        registry.addConverter(getIdToJUserConverter());
        registry.addConverter(getStringToJUserConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}

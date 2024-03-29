// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Wallet.proto

package alv.wallet;

/**
 * Protobuf enum {@code alv.wallet.CurrencyType}
 */
public enum CurrencyType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>EUR = 0;</code>
   */
  EUR(0),
  /**
   * <code>USD = 1;</code>
   */
  USD(1),
  /**
   * <code>GBP = 2;</code>
   */
  GBP(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>EUR = 0;</code>
   */
  public static final int EUR_VALUE = 0;
  /**
   * <code>USD = 1;</code>
   */
  public static final int USD_VALUE = 1;
  /**
   * <code>GBP = 2;</code>
   */
  public static final int GBP_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static CurrencyType valueOf(int value) {
    return forNumber(value);
  }

  public static CurrencyType forNumber(int value) {
    switch (value) {
      case 0: return EUR;
      case 1: return USD;
      case 2: return GBP;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<CurrencyType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      CurrencyType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<CurrencyType>() {
          public CurrencyType findValueByNumber(int number) {
            return CurrencyType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return alv.wallet.Wallet.getDescriptor().getEnumTypes().get(0);
  }

  private static final CurrencyType[] VALUES = values();

  public static CurrencyType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private CurrencyType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:alv.wallet.CurrencyType)
}


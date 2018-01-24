// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/protobuf/RemotingCommand.proto

package com.yimq.remoting.protocol;

public final class RemotingCommandProto {
  private RemotingCommandProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface RemotingCommandOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.yimq.remoting.protobuf.RemotingCommand)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 requestId = 1;</code>
     */
    boolean hasRequestId();
    /**
     * <code>required int32 requestId = 1;</code>
     */
    int getRequestId();

    /**
     * <pre>
     * 1:request command 2:response command 
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    boolean hasType();
    /**
     * <pre>
     * 1:request command 2:response command 
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    int getType();

    /**
     * <code>optional int32 code = 3;</code>
     */
    boolean hasCode();
    /**
     * <code>optional int32 code = 3;</code>
     */
    int getCode();

    /**
     * <code>optional string remark = 4;</code>
     */
    boolean hasRemark();
    /**
     * <code>optional string remark = 4;</code>
     */
    java.lang.String getRemark();
    /**
     * <code>optional string remark = 4;</code>
     */
    com.google.protobuf.ByteString
        getRemarkBytes();

    /**
     * <code>optional bytes body = 5;</code>
     */
    boolean hasBody();
    /**
     * <code>optional bytes body = 5;</code>
     */
    com.google.protobuf.ByteString getBody();
  }
  /**
   * Protobuf type {@code com.yimq.remoting.protobuf.RemotingCommand}
   */
  public  static final class RemotingCommand extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.yimq.remoting.protobuf.RemotingCommand)
      RemotingCommandOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use RemotingCommand.newBuilder() to construct.
    private RemotingCommand(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private RemotingCommand() {
      requestId_ = 0;
      type_ = 0;
      code_ = 0;
      remark_ = "";
      body_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private RemotingCommand(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              requestId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              type_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              code_ = input.readInt32();
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000008;
              remark_ = bs;
              break;
            }
            case 42: {
              bitField0_ |= 0x00000010;
              body_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.yimq.remoting.protocol.RemotingCommandProto.internal_static_com_yimq_remoting_protobuf_RemotingCommand_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yimq.remoting.protocol.RemotingCommandProto.internal_static_com_yimq_remoting_protobuf_RemotingCommand_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand.class, com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand.Builder.class);
    }

    private int bitField0_;
    public static final int REQUESTID_FIELD_NUMBER = 1;
    private int requestId_;
    /**
     * <code>required int32 requestId = 1;</code>
     */
    public boolean hasRequestId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 requestId = 1;</code>
     */
    public int getRequestId() {
      return requestId_;
    }

    public static final int TYPE_FIELD_NUMBER = 2;
    private int type_;
    /**
     * <pre>
     * 1:request command 2:response command 
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 1:request command 2:response command 
     * </pre>
     *
     * <code>required int32 type = 2;</code>
     */
    public int getType() {
      return type_;
    }

    public static final int CODE_FIELD_NUMBER = 3;
    private int code_;
    /**
     * <code>optional int32 code = 3;</code>
     */
    public boolean hasCode() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 code = 3;</code>
     */
    public int getCode() {
      return code_;
    }

    public static final int REMARK_FIELD_NUMBER = 4;
    private volatile java.lang.Object remark_;
    /**
     * <code>optional string remark = 4;</code>
     */
    public boolean hasRemark() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string remark = 4;</code>
     */
    public java.lang.String getRemark() {
      java.lang.Object ref = remark_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          remark_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string remark = 4;</code>
     */
    public com.google.protobuf.ByteString
        getRemarkBytes() {
      java.lang.Object ref = remark_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        remark_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BODY_FIELD_NUMBER = 5;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>optional bytes body = 5;</code>
     */
    public boolean hasBody() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional bytes body = 5;</code>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasRequestId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, requestId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, type_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, code_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, remark_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, body_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, requestId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, type_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, code_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, remark_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, body_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand)) {
        return super.equals(obj);
      }
      com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand other = (com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand) obj;

      boolean result = true;
      result = result && (hasRequestId() == other.hasRequestId());
      if (hasRequestId()) {
        result = result && (getRequestId()
            == other.getRequestId());
      }
      result = result && (hasType() == other.hasType());
      if (hasType()) {
        result = result && (getType()
            == other.getType());
      }
      result = result && (hasCode() == other.hasCode());
      if (hasCode()) {
        result = result && (getCode()
            == other.getCode());
      }
      result = result && (hasRemark() == other.hasRemark());
      if (hasRemark()) {
        result = result && getRemark()
            .equals(other.getRemark());
      }
      result = result && (hasBody() == other.hasBody());
      if (hasBody()) {
        result = result && getBody()
            .equals(other.getBody());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasRequestId()) {
        hash = (37 * hash) + REQUESTID_FIELD_NUMBER;
        hash = (53 * hash) + getRequestId();
      }
      if (hasType()) {
        hash = (37 * hash) + TYPE_FIELD_NUMBER;
        hash = (53 * hash) + getType();
      }
      if (hasCode()) {
        hash = (37 * hash) + CODE_FIELD_NUMBER;
        hash = (53 * hash) + getCode();
      }
      if (hasRemark()) {
        hash = (37 * hash) + REMARK_FIELD_NUMBER;
        hash = (53 * hash) + getRemark().hashCode();
      }
      if (hasBody()) {
        hash = (37 * hash) + BODY_FIELD_NUMBER;
        hash = (53 * hash) + getBody().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.yimq.remoting.protobuf.RemotingCommand}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.yimq.remoting.protobuf.RemotingCommand)
        com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommandOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.yimq.remoting.protocol.RemotingCommandProto.internal_static_com_yimq_remoting_protobuf_RemotingCommand_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.yimq.remoting.protocol.RemotingCommandProto.internal_static_com_yimq_remoting_protobuf_RemotingCommand_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand.class, com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand.Builder.class);
      }

      // Construct using com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        requestId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        type_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        code_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        remark_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        body_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.yimq.remoting.protocol.RemotingCommandProto.internal_static_com_yimq_remoting_protobuf_RemotingCommand_descriptor;
      }

      public com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand getDefaultInstanceForType() {
        return com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand.getDefaultInstance();
      }

      public com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand build() {
        com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand buildPartial() {
        com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand result = new com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.requestId_ = requestId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.type_ = type_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.code_ = code_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.remark_ = remark_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.body_ = body_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand) {
          return mergeFrom((com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand other) {
        if (other == com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand.getDefaultInstance()) return this;
        if (other.hasRequestId()) {
          setRequestId(other.getRequestId());
        }
        if (other.hasType()) {
          setType(other.getType());
        }
        if (other.hasCode()) {
          setCode(other.getCode());
        }
        if (other.hasRemark()) {
          bitField0_ |= 0x00000008;
          remark_ = other.remark_;
          onChanged();
        }
        if (other.hasBody()) {
          setBody(other.getBody());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasRequestId()) {
          return false;
        }
        if (!hasType()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int requestId_ ;
      /**
       * <code>required int32 requestId = 1;</code>
       */
      public boolean hasRequestId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 requestId = 1;</code>
       */
      public int getRequestId() {
        return requestId_;
      }
      /**
       * <code>required int32 requestId = 1;</code>
       */
      public Builder setRequestId(int value) {
        bitField0_ |= 0x00000001;
        requestId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 requestId = 1;</code>
       */
      public Builder clearRequestId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        requestId_ = 0;
        onChanged();
        return this;
      }

      private int type_ ;
      /**
       * <pre>
       * 1:request command 2:response command 
       * </pre>
       *
       * <code>required int32 type = 2;</code>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <pre>
       * 1:request command 2:response command 
       * </pre>
       *
       * <code>required int32 type = 2;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <pre>
       * 1:request command 2:response command 
       * </pre>
       *
       * <code>required int32 type = 2;</code>
       */
      public Builder setType(int value) {
        bitField0_ |= 0x00000002;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 1:request command 2:response command 
       * </pre>
       *
       * <code>required int32 type = 2;</code>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        type_ = 0;
        onChanged();
        return this;
      }

      private int code_ ;
      /**
       * <code>optional int32 code = 3;</code>
       */
      public boolean hasCode() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 code = 3;</code>
       */
      public int getCode() {
        return code_;
      }
      /**
       * <code>optional int32 code = 3;</code>
       */
      public Builder setCode(int value) {
        bitField0_ |= 0x00000004;
        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 code = 3;</code>
       */
      public Builder clearCode() {
        bitField0_ = (bitField0_ & ~0x00000004);
        code_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object remark_ = "";
      /**
       * <code>optional string remark = 4;</code>
       */
      public boolean hasRemark() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional string remark = 4;</code>
       */
      public java.lang.String getRemark() {
        java.lang.Object ref = remark_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            remark_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string remark = 4;</code>
       */
      public com.google.protobuf.ByteString
          getRemarkBytes() {
        java.lang.Object ref = remark_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          remark_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string remark = 4;</code>
       */
      public Builder setRemark(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        remark_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string remark = 4;</code>
       */
      public Builder clearRemark() {
        bitField0_ = (bitField0_ & ~0x00000008);
        remark_ = getDefaultInstance().getRemark();
        onChanged();
        return this;
      }
      /**
       * <code>optional string remark = 4;</code>
       */
      public Builder setRemarkBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        remark_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString body_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes body = 5;</code>
       */
      public boolean hasBody() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional bytes body = 5;</code>
       */
      public com.google.protobuf.ByteString getBody() {
        return body_;
      }
      /**
       * <code>optional bytes body = 5;</code>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes body = 5;</code>
       */
      public Builder clearBody() {
        bitField0_ = (bitField0_ & ~0x00000010);
        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.yimq.remoting.protobuf.RemotingCommand)
    }

    // @@protoc_insertion_point(class_scope:com.yimq.remoting.protobuf.RemotingCommand)
    private static final com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand();
    }

    public static com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<RemotingCommand>
        PARSER = new com.google.protobuf.AbstractParser<RemotingCommand>() {
      public RemotingCommand parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RemotingCommand(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<RemotingCommand> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RemotingCommand> getParserForType() {
      return PARSER;
    }

    public com.yimq.remoting.protocol.RemotingCommandProto.RemotingCommand getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_yimq_remoting_protobuf_RemotingCommand_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_yimq_remoting_protobuf_RemotingCommand_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"src/protobuf/RemotingCommand.proto\022\032co" +
      "m.yimq.remoting.protobuf\"^\n\017RemotingComm" +
      "and\022\021\n\trequestId\030\001 \002(\005\022\014\n\004type\030\002 \002(\005\022\014\n\004" +
      "code\030\003 \001(\005\022\016\n\006remark\030\004 \001(\t\022\014\n\004body\030\005 \001(\014" +
      "B2\n\032com.yimq.remoting.protocolB\024Remoting" +
      "CommandProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_yimq_remoting_protobuf_RemotingCommand_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_yimq_remoting_protobuf_RemotingCommand_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_yimq_remoting_protobuf_RemotingCommand_descriptor,
        new java.lang.String[] { "RequestId", "Type", "Code", "Remark", "Body", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
